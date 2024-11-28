const apiBaseURL = 'http://localhost:8080';

// Função para cadastrar uma nova alergia
document.getElementById('alergiaForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Evita o recarregamento da página

    const nomeAlergia = document.getElementById('nomeAlergia').value;

    fetch(`${apiBaseURL}/alergias`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nome: nomeAlergia }),
    })
        .then(response => {
            if (response.ok) {
                document.getElementById("nomeAlergia").value = "";
                return response.json();
            } else {
                throw new Error('Erro ao cadastrar alergia.');
            }
        })
        .then(data => {
            document.getElementById('feedback').textContent = 'Alergia cadastrada com sucesso!';
        })
        .catch(error => {
            document.getElementById('feedback').textContent = 'Erro ao cadastrar alergia.';
            console.error(error);
        });
});