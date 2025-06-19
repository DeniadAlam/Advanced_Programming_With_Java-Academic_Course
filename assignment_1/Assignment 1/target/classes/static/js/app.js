function loadProducts() {
    fetch('/api/products')
        .then(res => res.json())
        .then(data => {
            document.getElementById("output").textContent = JSON.stringify(data, null, 2);
        });
}