const apiBaseURL = 'http://localhost:8080'; // Altere para o endereço da sua API

// Função para carregar usuários no select
function carregarUsuarios() {
    fetch(`${apiBaseURL}/usuarios`)
        .then(response => response.json())
        .then(data => {
            const usuarioSelect = document.getElementById('usuario');
            usuarioSelect.innerHTML = '<option value="">Selecione um usuário</option>';
            // Acesse o array dentro de `content`
            data.content.forEach(usuario => {
                const option = document.createElement('option');
                option.value = usuario.id;
                option.textContent = usuario.nome;
                usuarioSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar usuários:', error);
            document.getElementById('usuario').innerHTML = '<option value="">Erro ao carregar usuários</option>';
        });
}

// Função para carregar vacinas no select
function carregarVacinas() {
    fetch(`${apiBaseURL}/vacinas`)
        .then(response => response.json())
        .then(data => {
            const vacinaSelect = document.getElementById('vacina');
            vacinaSelect.innerHTML = '<option value="">Selecione uma vacina</option>';
            // Acesse o array dentro de `content`
            data.content.forEach(vacina => {
                const option = document.createElement('option');
                option.value = vacina.id;
                option.textContent = vacina.titulo; // Assumindo que a vacina tem o campo `nome`
                vacinaSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar vacinas:', error);
            document.getElementById('vacina').innerHTML = '<option value="">Erro ao carregar vacinas</option>';
        });
}

// Função para cadastrar agenda
document.getElementById('agendaForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const data = document.getElementById('data').value;
    const hora = document.getElementById('hora').value;
    const observacoes = document.getElementById('observacoes').value;
    const usuarioId = document.getElementById('usuario').value;
    const vacinaId = document.getElementById('vacina').value;

    const agendaData = {
        data: data,
        hora: hora,
        observacoes: observacoes,
        usuario: { id: usuarioId },
        vacina: { id: vacinaId }
    };

    fetch(`${apiBaseURL}/agendas`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(agendaData)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Erro ao cadastrar agenda');
            }
        })
        .then(data => {
            document.getElementById('feedback').innerHTML = `<p style="color: green;">Agenda cadastrada com sucesso!</p>`;
            console.log('Agenda cadastrada:', data);
        })
        .catch(error => {
            console.error('Erro ao cadastrar agenda:', error);
            document.getElementById('feedback').innerHTML = `<p style="color: red;">Erro ao cadastrar agenda.</p>`;
        });
});

// Carregar usuários e vacinas ao carregar a página
document.addEventListener('DOMContentLoaded', function () {
    carregarUsuarios();
    carregarVacinas();
});
