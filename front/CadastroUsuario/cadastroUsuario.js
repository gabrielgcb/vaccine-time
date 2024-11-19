const apiBaseURL = 'http://localhost:8080';

function carregarAlergias() {
    fetch(`${apiBaseURL}/alergias`)
        .then(response => response.json())
        .then(data => {
            const alergiasSelect = document.getElementById('alergias');
            alergiasSelect.innerHTML = '';
            data.content.forEach(alergia => {
                const option = document.createElement('option');
                option.value = alergia.id;
                option.textContent = alergia.nome;
                alergiasSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar alergias:', error);
        });
}

document.getElementById('usuarioForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const usuario = {
        nome: document.getElementById('nome').value,
        dataNascimento: document.getElementById('dataNascimento').value,
        sexo: document.getElementById('sexo').value,
        endereco: {
            logradouro: document.getElementById('logradouro').value,
            numero: document.getElementById('numero').value,
            setor: document.getElementById('setor').value,
            cidade: document.getElementById('cidade').value,
            estado: document.getElementById('estado').value,
            uf: document.getElementById('uf').value
        },
        alergias: Array.from(document.getElementById('alergias').selectedOptions).map(option => ({
            id: option.value
        }))
    };

    fetch(`${apiBaseURL}/usuarios`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Erro ao cadastrar usuário');
            }
        })
        .then(data => {
            document.getElementById('feedback').textContent = 'Usuário cadastrado com sucesso!';
            console.log(data);
        })
        .catch(error => {
            document.getElementById('feedback').textContent = 'Erro ao cadastrar usuário.';
            console.error(error);
        });
});

document.addEventListener('DOMContentLoaded', carregarAlergias);
