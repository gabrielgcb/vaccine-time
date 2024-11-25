let currentPage = 0;

function loadUsuarios(page) {
    fetch(`http://127.0.0.1:8080/usuarios?page=${page}&sort=nome`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#usuariosTable tbody");
            tableBody.innerHTML = "";

            data.content.forEach(usuario => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${usuario.nome}</td>
                    <td>${usuario.dataNascimento}</td>
                    <td>${usuario.sexo}</td>
                    <td>${usuario.endereco.cidade}</td>
                    <td>${usuario.endereco.uf}</td>
                    <td id="coluna-acoes">
                        <i class="fa fa-trash lixeira" onclick="excluirUsuario(${usuario.id})"></i>
                    </td>
                `;
                tableBody.appendChild(row);
            });

            document.getElementById("currentPage").innerText = page + 1;
        })
        .catch(error => console.error("Erro ao carregar usuários:", error));
}

function excluirUsuario(id) {
    if (confirm("Tem certeza que deseja excluir este usuário?")) {
        fetch(`http://127.0.0.1:8080/usuarios/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.ok) {
                alert("Usuário excluído com sucesso!");
                loadUsuarios(currentPage); // Recarregar a lista após exclusão
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
        loadUsuarios(currentPage);
    }
});

document.getElementById("nextPage").addEventListener("click", () => {
    currentPage++;
    loadUsuarios(currentPage);
});

// Carregar a página inicial
loadUsuarios(currentPage);
