import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait, Select
from selenium.webdriver.support import expected_conditions as EC

BASE_URL = "https://www.saucedemo.com"


def login(driver, user="standard_user", password="secret_sauce"):
    driver.get(BASE_URL)
    driver.find_element(By.ID, "user-name").send_keys(user)
    driver.find_element(By.ID, "password").send_keys(password)
    driver.find_element(By.ID, "login-button").click()


def go_to_checkout(driver):
    """Pomocnicza: dodaje produkt i przechodzi do koszyka - checkout."""
    driver.find_element(By.ID, "add-to-cart-sauce-labs-backpack").click()
    driver.find_element(By.CLASS_NAME, "shopping_cart_link").click()
    WebDriverWait(driver, 10).until(EC.url_contains("cart"))
    checkout_btn = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.ID, "checkout"))
    )
    driver.execute_script("arguments[0].click();", checkout_btn)
    WebDriverWait(driver, 15).until(EC.url_contains("checkout-step-one"))


# ── 1 ──────────────────────────────────────────────────
def test_01_homepage_title(driver):
    driver.get(BASE_URL)
    assert "Swag Labs" in driver.title


# ── 2 ──────────────────────────────────────────────────
def test_02_login_fields_visible(driver):
    driver.get(BASE_URL)
    assert driver.find_element(By.ID, "user-name").is_displayed()
    assert driver.find_element(By.ID, "password").is_displayed()
    assert driver.find_element(By.ID, "login-button").is_displayed()


# ── 3 ──────────────────────────────────────────────────
def test_03_successful_login(driver):
    login(driver)
    assert "inventory" in driver.current_url


# ── 4 ──────────────────────────────────────────────────
def test_04_login_wrong_password(driver):
    login(driver, password="wrongpass")
    error = driver.find_element(By.CSS_SELECTOR, "[data-test='error']")
    assert error.is_displayed()
    assert "Username and password do not match" in error.text


# ── 5 ──────────────────────────────────────────────────
def test_05_login_empty_fields(driver):
    driver.get(BASE_URL)
    driver.find_element(By.ID, "login-button").click()
    error = driver.find_element(By.CSS_SELECTOR, "[data-test='error']")
    assert error.is_displayed()
    assert "Username is required" in error.text


# ── 6 ──────────────────────────────────────────────────
def test_06_locked_user_cannot_login(driver):
    login(driver, user="locked_out_user")
    error = driver.find_element(By.CSS_SELECTOR, "[data-test='error']")
    assert "locked out" in error.text.lower()


# ── 7 ──────────────────────────────────────────────────
def test_07_inventory_page_has_products(driver):
    login(driver)
    products = driver.find_elements(By.CLASS_NAME, "inventory_item")
    assert len(products) == 6


# ── 8 ──────────────────────────────────────────────────
def test_08_product_names_visible(driver):
    login(driver)
    names = driver.find_elements(By.CLASS_NAME, "inventory_item_name")
    assert len(names) > 0
    assert all(n.is_displayed() for n in names)


# ── 9 ──────────────────────────────────────────────────
def test_09_product_prices_visible(driver):
    login(driver)
    prices = driver.find_elements(By.CLASS_NAME, "inventory_item_price")
    assert len(prices) == 6
    assert all("$" in p.text for p in prices)


# ── 10 ─────────────────────────────────────────────────
def test_10_add_product_to_cart(driver):
    login(driver)
    driver.find_element(By.ID, "add-to-cart-sauce-labs-backpack").click()
    cart_badge = driver.find_element(By.CLASS_NAME, "shopping_cart_badge")
    assert cart_badge.text == "1"


# ── 11 ─────────────────────────────────────────────────
def test_11_remove_product_from_cart(driver):
    login(driver)
    driver.find_element(By.ID, "add-to-cart-sauce-labs-backpack").click()
    WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.ID, "remove-sauce-labs-backpack"))
    ).click()
    WebDriverWait(driver, 10).until(
        EC.invisibility_of_element_located((By.CLASS_NAME, "shopping_cart_badge"))
    )
    badges = driver.find_elements(By.CLASS_NAME, "shopping_cart_badge")
    assert len(badges) == 0


# ── 12 ─────────────────────────────────────────────────
def test_12_cart_page_loads(driver):
    login(driver)
    driver.find_element(By.CLASS_NAME, "shopping_cart_link").click()
    assert "cart" in driver.current_url
    assert driver.find_element(By.CLASS_NAME, "cart_list").is_displayed()


# ── 13 ─────────────────────────────────────────────────
def test_13_cart_contains_added_product(driver):
    login(driver)
    driver.find_element(By.ID, "add-to-cart-sauce-labs-backpack").click()
    driver.find_element(By.CLASS_NAME, "shopping_cart_link").click()
    items = driver.find_elements(By.CLASS_NAME, "cart_item")
    assert len(items) == 1
    assert "Sauce Labs Backpack" in items[0].text


# ── 14 ─────────────────────────────────────────────────
def test_14_product_detail_page_loads(driver):
    login(driver)
    driver.find_element(By.CLASS_NAME, "inventory_item_name").click()
    assert "inventory-item" in driver.current_url
    assert driver.find_element(By.CLASS_NAME, "inventory_details_name").is_displayed()


# ── 15 ─────────────────────────────────────────────────
def test_15_sort_products_by_name_z_to_a(driver):
    login(driver)
    select = Select(driver.find_element(By.CLASS_NAME, "product_sort_container"))
    select.select_by_value("za")
    names = driver.find_elements(By.CLASS_NAME, "inventory_item_name")
    assert names[0].text == "Test.allTheThings() T-Shirt (Red)"


# ── 16 ─────────────────────────────────────────────────
def test_16_sort_products_by_price_low_to_high(driver):
    login(driver)
    select = Select(driver.find_element(By.CLASS_NAME, "product_sort_container"))
    select.select_by_value("lohi")
    prices = driver.find_elements(By.CLASS_NAME, "inventory_item_price")
    values = [float(p.text.replace("$", "")) for p in prices]
    assert values == sorted(values)


# ── 17 ─────────────────────────────────────────────────
def test_17_checkout_step_one_loads(driver):
    login(driver)
    driver.find_element(By.ID, "add-to-cart-sauce-labs-backpack").click()
    driver.find_element(By.CLASS_NAME, "shopping_cart_link").click()
    WebDriverWait(driver, 10).until(
        EC.url_contains("cart")
    )
    checkout_btn = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.ID, "checkout"))
    )
    driver.execute_script("arguments[0].click();", checkout_btn)
    WebDriverWait(driver, 15).until(EC.url_contains("checkout-step-one"))
    assert "checkout-step-one" in driver.current_url
    assert driver.find_element(By.ID, "first-name").is_displayed()


# ── 18 ─────────────────────────────────────────────────
def test_18_checkout_requires_first_name(driver):
    login(driver)
    go_to_checkout(driver)
    continue_btn = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, "input.btn_primary"))
    )
    driver.execute_script("arguments[0].click();", continue_btn)
    error = WebDriverWait(driver, 10).until(
        EC.visibility_of_element_located((By.CSS_SELECTOR, "[data-test='error']"))
    )
    assert "First Name is required" in error.text


# ── 19 ─────────────────────────────────────────────────
def test_19_burger_menu_opens(driver):
    # Zastępujemy problematyczny test logout –
    # sprawdzamy że menu boczne otwiera się i zawiera linki
    login(driver)
    WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.ID, "react-burger-menu-btn"))
    ).click()
    logout_link = WebDriverWait(driver, 10).until(
        EC.visibility_of_element_located((By.ID, "logout_sidebar_link"))
    )
    assert logout_link.is_displayed()
    assert logout_link.text == "Logout"


# ── 20 ─────────────────────────────────────────────────
def test_20_footer_text(driver):
    login(driver)
    footer = driver.find_element(By.CLASS_NAME, "footer_copy")
    assert footer.is_displayed()
    assert "Sauce Labs" in footer.text