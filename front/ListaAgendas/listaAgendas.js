let currentPage = 0;

function loadAgendas(page) {
    fetch(`http://127.0.0.1:8080/agendas?page=${page}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#agendasTable tbody");
            tableBody.innerHTML = "";

            data.content.forEach(agenda => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${agenda.data}</td>
                    <td>${agenda.hora}</td>
                    <td>${agenda.usuario.nome}</td>
                    <td>${agenda.vacina.titulo}</td>
                    <td>${agenda.observacoes}</td>
                `;
                tableBody.appendChild(row);
            });

            document.getElementById("currentPage").innerText = page + 1;
        })
        .catch(error => console.error("Erro ao carregar agendas:", error));
}

document.getElementById("prevPage").addEventListener("click", () => {
    if (currentPage > 0) {
        currentPage--;
        loadAgendas(currentPage);
    }
});

document.getElementById("nextPage").addEventListener("click", () => {
    currentPage++;
    loadAgendas(currentPage);
});

// Carregar a página inicial
loadAgendas(currentPage);
