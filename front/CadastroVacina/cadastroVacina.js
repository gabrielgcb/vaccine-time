// Função para cadastrar uma vacina
function cadastrarVacina() {
    // Obtém os valores dos campos do formulário
    const titulo = document.getElementById("titulo").value.trim();
    const descricao = document.getElementById("descricao").value.trim();
    const doses = parseInt(document.getElementById("doses").value, 10);
    const periodicidade = parseInt(document.getElementById("periodicidade").value, 10);
    const intervalo = parseInt(document.getElementById("intervalo").value, 10);

    // Cria o objeto de dados para o cadastro
    const requestBody = {
        titulo: titulo,
        descricao: descricao,
        doses: doses,
        periodicidade: periodicidade,
        intervalo: intervalo
    };

    // Faz a requisição ao backend
    fetch("http://127.0.0.1:8080/vacinas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => {
        if (response.ok) {
            alert("Vacina cadastrada com sucesso!");
            // Limpa os campos do formulário após o cadastro
            document.getElementById("titulo").value = "";
            document.getElementById("descricao").value = "";
            document.getElementById("doses").value = "";
            document.getElementById("periodicidade").value = "";
            document.getElementById("intervalo").value = "";
        } else {
            throw new Error('Erro ao cadastrar alergia.');
        }
    })
    .catch(error => {
        console.error("Erro ao cadastrar vacina:", error);
        alert(error.message);
    });
}

// Adiciona o evento de clique no botão de cadastro
document.getElementById("cadastrarVacina").addEventListener("click", cadastrarVacina);
