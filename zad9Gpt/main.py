"""
Serwis do łączenia z lokalnym modelem Ollama (Mistral)
Uruchomienie: uvicorn main:app --reload --port 8000
"""

import httpx
from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel

app = FastAPI(title="Ollama GPT Service", version="1.0.0")

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_methods=["*"],
    allow_headers=["*"],
)

OLLAMA_URL = "http://127.0.0.1:11434/api/generate"
MODEL_NAME = "mistral"


class ChatRequest(BaseModel):
    message: str


class ChatResponse(BaseModel):
    reply: str


@app.get("/health")
async def health():
    return {"status": "ok", "model": "ollama", "running": True}


@app.post("/chat", response_model=ChatResponse)
async def chat(req: ChatRequest):
    try:
        async with httpx.AsyncClient(timeout=240.0) as client:
            response = await client.post(
                OLLAMA_URL,
                json={
                    "model": MODEL_NAME,
                    "prompt": f"""
                    You are shop assistant in a clothing store.
                    Message from customer: {req.message}
                    """,
                    "stream": False
                }
            )

        print("STATUS:", response.status_code)
        print("BODY:", response.text)

        data = response.json()
        print("JSON:", data)

        return ChatResponse(
            reply=data["response"]
        )

    except Exception as e:
        import traceback
        traceback.print_exc()
        raise HTTPException(status_code=500, detail=str(e))