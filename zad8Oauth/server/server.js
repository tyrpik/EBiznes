const express = require("express");
const sqlite3 = require("sqlite3").verbose();
const bcrypt = require("bcrypt");
const session = require("express-session");
const cors = require("cors");

const app = express();

app.use(express.json());

app.use(cors({
    origin: "http://localhost:3000",
    credentials: true
}));

app.use(session({
    secret: "secret123",
    resave: false,
    saveUninitialized: false
}));

// baza danych
const db = new sqlite3.Database("users.db");

// tworzenie tabeli
db.run(`
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE,
    password TEXT
)
`);


// Rrejestracja
app.post("/register", async (req, res) => {
    const { username, password } = req.body;

    const hashedPassword = await bcrypt.hash(password, 10);

    db.run(
        "INSERT INTO users(username, password) VALUES (?, ?)",
        [username, hashedPassword],
        function(err) {
            if (err) {
                return res.status(400).json({ error: "User exists" });
            }

            res.json({ message: "User created" });
        }
    );
});


// Logowanie
app.post("/login", (req, res) => {
    const { username, password } = req.body;

    db.get(
        "SELECT * FROM users WHERE username = ?",
        [username],
        async (err, user) => {

            if (!user) {
                return res.status(401).json({
                    error: "Wrong credentials"
                });
            }

            const valid = await bcrypt.compare(
                password,
                user.password
            );

            if (!valid) {
                return res.status(401).json({
                    error: "Wrong credentials"
                });
            }

            // zapis sesji
            req.session.userId = user.id;

            res.json({
                message: "Logged in"
            });
        }
    );
});


// sprawdzenie sesji
app.get("/me", (req, res) => {

    if (!req.session.userId) {
        return res.status(401).json({
            error: "Not logged"
        });
    }

    res.json({
        userId: req.session.userId
    });
});


// logout
app.post("/logout", (req, res) => {
    req.session.destroy();
    res.json({
        message: "Logged out"
    });
});

app.listen(5000, () => {
    console.log("Server started");
});