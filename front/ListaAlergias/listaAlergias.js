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
                    <td id="coluna-acoes">
                        <i class="fa fa-trash lixeira" onclick="excluirAlergia(${alergia.id})"></i>
                    </td>
                `;
                tableBody.appendChild(row);
            });

            document.getElementById("currentPage").innerText = page + 1;
        })
        .catch(error => console.error("Erro ao carregar alergias:", error));
}

function excluirAlergia(id) {
    if (confirm("Tem certeza que deseja excluir esta alergia?")) {
        fetch(`http://127.0.0.1:8080/alergias/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.ok) {
                alert("Alergia excluída com sucesso!");
                loadAlergias(currentPage); // Recarregar a lista após exclusão
            } else {
                response.text().then(text => alert("Erro ao excluir: " + text));
            }
        })
        .catch(error => console.error("Erro ao excluir alergia:", error));
    }
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
