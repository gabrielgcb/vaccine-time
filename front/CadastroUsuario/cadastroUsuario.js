const apiBaseURL = 'http://localhost:8080';

// Carrega alergias ao carregar a página
function carregarAlergias() {
    fetch(`${apiBaseURL}/alergias`)
        .then(response => response.json())
        .then(data => {
            const alergiasContainer = document.getElementById('alergias');
            alergiasContainer.innerHTML = ''; // Limpa as opções existentes

            if (data.content.length === 0) {
                alergiasContainer.textContent = 'Nenhuma alergia cadastrada';
                return;
            }

            data.content.forEach(alergia => {
                const checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.id = `alergia-${alergia.id}`;
                checkbox.value = alergia.id;

                const label = document.createElement('label');
                label.htmlFor = `alergia-${alergia.id}`;
                label.textContent = alergia.nome;

                const div = document.createElement('div');
                div.appendChild(checkbox);
                div.appendChild(label);

                alergiasContainer.appendChild(div);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar alergias:', error);
            const alergiasContainer = document.getElementById('alergias');
            alergiasContainer.textContent = 'Erro ao carregar alergias';
        });
}

// Envia os dados do formulário para o backend
document.getElementById('usuarioForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const alergiasSelecionadas = Array.from(document.querySelectorAll('#alergias input[type="checkbox"]:checked'))
        .map(checkbox => ({
            id: parseInt(checkbox.value, 10)
        }));

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
        alergias: alergiasSelecionadas
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
                return response.text().then(text => {
                    throw new Error(text || 'Erro ao cadastrar usuário');
                });
            }
        })
        .then(data => {
            document.getElementById('feedback').textContent = 'Usuário cadastrado com sucesso!';
            console.log(data);
        })
        .catch(error => {
            document.getElementById('feedback').textContent = `Erro ao cadastrar usuário: ${error.message}`;
            console.error(error);
        });
});

// Carrega as alergias quando a página estiver pronta
document.addEventListener('DOMContentLoaded', carregarAlergias);
