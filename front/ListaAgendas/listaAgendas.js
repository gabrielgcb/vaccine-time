let currentPage = 0;

function loadAgendas(page) {
    // Captura os valores dos filtros
    const usuarioFiltro = document.getElementById("usuarioFiltro").value.trim();
    const vacinaFiltro = document.getElementById("vacinaFiltro").value.trim();

    // Cria a URL para o endpoint com os filtros e paginação
    let url = `http://127.0.0.1:8080/agendas/filtro?page=${page}&size=10&sort=data`;
    if (usuarioFiltro) url += `&usuarioNome=${encodeURIComponent(usuarioFiltro)}`;
    if (vacinaFiltro) url += `&vacinaTitulo=${encodeURIComponent(vacinaFiltro)}`;

    // Faz a requisição ao backend
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#agendasTable tbody");
            tableBody.innerHTML = "";

            // Preenche a tabela com os resultados
            data.content.forEach(agenda => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${agenda.id}</td>
                    <td>${agenda.data}</td>
                    <td>${agenda.hora}</td>
                    <td>${agenda.usuario.nome}</td>
                    <td>${agenda.vacina.titulo}</td>
                    <td>${agenda.observacoes}</td>
                    <td>${agenda.situacao}</td>
                    <td>${agenda.dataSituacao}</td>
                    <td>
                        <button class="acao-check" data-id="${agenda.id}" title="Marcar como realizada">✔️</button>
                        <button class="acao-cancelar" data-id="${agenda.id}" title="Marcar como cancelada">❌</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });

            // Adiciona eventos de clique para os botões de ação
            document.querySelectorAll(".acao-check").forEach(button => {
                button.addEventListener("click", () => atualizarSituacao(button.dataset.id, "REALIZADO"));
            });

            document.querySelectorAll(".acao-cancelar").forEach(button => {
                button.addEventListener("click", () => atualizarSituacao(button.dataset.id, "CANCELADO"));
            });

            // Atualiza informações da paginação
            document.getElementById("currentPage").innerText = page + 1;
            document.getElementById("prevPage").disabled = page === 0;
            document.getElementById("nextPage").disabled = data.last;
        })
        .catch(error => console.error("Erro ao carregar agendas:", error));
}
function atualizarSituacao(id, novaSituacao) {
    fetch(`http://127.0.0.1:8080/agendas/${id}/situacao?novaSituacao=${novaSituacao}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao atualizar situação da agenda.");
            }
            return response.json();
        })
        .then(data => {
            alert(`Agenda atualizada para: ${novaSituacao}`);
            loadAgendas(currentPage); // Recarregar a tabela
        })
        .catch(error => console.error("Erro:", error));
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

document.getElementById("applyFilters").addEventListener("click", () => {
    loadAgendas(0); // Reinicia na primeira página com os filtros aplicados
});

// Carregar a página inicial
loadAgendas(currentPage);
