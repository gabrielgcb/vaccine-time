let currentPage = 0;

function loadVacinas(page) {
    fetch(`http://127.0.0.1:8080/vacinas?page=${page}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#vacinasTable tbody");
            tableBody.innerHTML = "";

            data.content.forEach(vacina => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${vacina.titulo}</td>
                    <td>${vacina.descricao}</td>
                    <td>${vacina.doses}</td>
                    <td>${vacina.periodicidade}</td>
                    <td>${vacina.intervalo}</td>
                `;
                tableBody.appendChild(row);
            });

            document.getElementById("currentPage").innerText = page + 1;
        })
        .catch(error => console.error("Erro ao carregar vacinas:", error));
}

document.getElementById("prevPage").addEventListener("click", () => {
    if (currentPage > 0) {
        currentPage--;
        loadVacinas(currentPage);
    }
});

document.getElementById("nextPage").addEventListener("click", () => {
    currentPage++;
    loadVacinas(currentPage);
});

// Carregar a página inicial
loadVacinas(currentPage);
