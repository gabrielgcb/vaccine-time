let currentPage = 0;

function loadAlergias(page) {
    fetch(`http://127.0.0.1:8080/alergias?page=${page}&sort=nome`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#alergiasTable tbody");
            tableBody.innerHTML = "";

            data.content.forEach(alergia => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${alergia.nome}</td>
                `;
                tableBody.appendChild(row);
            });

            document.getElementById("currentPage").innerText = page + 1;
        })
        .catch(error => console.error("Erro ao carregar alergias:", error));
}

document.getElementById("prevPage").addEventListener("click", () => {
    if (currentPage > 0) {
        currentPage--;
        loadAlergias(currentPage);
    }
});

document.getElementById("nextPage").addEventListener("click", () => {
    currentPage++;
    loadAlergias(currentPage);
});

// Carregar a página inicial
loadAlergias(currentPage);
