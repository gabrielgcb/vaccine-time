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
                    <td id="coluna-acoes">
                        <i class="fa fa-trash lixeira" onclick="excluirVacina(${vacina.id})"></i>
                    </td>
                `;
                tableBody.appendChild(row);
            });

            document.getElementById("currentPage").innerText = page + 1;
        })
        .catch(error => console.error("Erro ao carregar vacinas:", error));
}

function excluirVacina(id) {
    if (confirm("Tem certeza que deseja excluir esta vacina?")) {
        fetch(`http://127.0.0.1:8080/vacinas/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.ok) {
                alert("Vacina excluída com sucesso!");
                loadVacinas(currentPage); // Recarregar a lista após exclusão
            } else {
                response.text().then(text => alert("Erro ao excluir: " + text));
            }
        })
        .catch(error => console.error("Erro ao excluir usuário:", error));
    }
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
