// Simulação de dados de salas (isso pode vir de uma API ou banco de dados)
let salas = [
    { id: 1, nome: "Sala 1" },
    { id: 2, nome: "Sala 2" },
    { id: 3, nome: "Sala 3" }
];

// Função para renderizar as salas na página
// Função para renderizar as salas na página
function renderSalas() {
    const section = document.querySelector('.section');
    section.innerHTML = ""; // Limpar a seção antes de renderizar

    salas.forEach(sala => {
        // Criando o HTML para cada sala
        const roomCard = document.createElement('div');
        roomCard.classList.add('room-card');
        roomCard.innerHTML = `
            <span>${sala.nome}</span>
            <button class="edit-btn" data-id="${sala.id}">Editar</button>
            <button class="delete-btn" data-id="${sala.id}">Excluir</button>
        `;
        section.appendChild(roomCard);
    });

    // Adicionar eventos para os botões de editar e excluir
    document.querySelectorAll('.edit-btn').forEach(button => {
        button.addEventListener('click', editarSala);
    });

    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', excluirSala);
    });
}

// Função para abrir o modal de criar sala
function criarSala() {
    const modal = document.getElementById('create-room-modal');
    modal.classList.add('show'); // Exibe o modal de criação de sala

    // Limpa os campos do modal antes de exibir
    document.getElementById('room-code').value = ''; // Campo código vazio
    document.getElementById('room-name').value = ''; // Campo nome vazio
}

// Função para submeter o formulário de criação de sala
function submitCreateRoom() {
    const codigo = document.getElementById('room-code').value;
    const nome = document.getElementById('room-name').value;

    if (codigo && nome) {
        const novaSala = {
            id: parseInt(codigo), // Usar o código fornecido pelo usuário como ID
            nome: nome
        };
        salas.push(novaSala); // Adiciona a nova sala à lista
        renderSalas(); // Re-renderiza as salas para incluir a nova sala

        // Esconde o modal após a criação
        const modal = document.getElementById('create-room-modal');
        modal.style.display = 'none';
    } else {
        alert("Por favor, preencha todos os campos.");
    }
}

// Função para cancelar a criação da sala
function cancelCreateRoom() {
    const modal = document.getElementById('create-room-modal');
    modal.style.display = 'none'; // Fecha o modal
}

// Função para editar uma sala
function editarSala(event) {
    const idSala = event.target.getAttribute('data-id');
    const sala = salas.find(s => s.id == idSala);

    if (sala) {
        // Preenche os campos do modal com os dados da sala
        document.getElementById('edit-room-code').value = sala.id;  // Exibe o código (ID) da sala (não editável)
        document.getElementById('edit-room-name').value = sala.nome; // Exibe o nome da sala (editável)

        // Exibe o modal de edição
        document.getElementById('edit-room-modal').classList.add('show');

        // Adicionar evento para salvar alterações
        document.getElementById('save-room').addEventListener('click', function() {
            const novoNome = document.getElementById('edit-room-name').value;
            if (novoNome) {
                sala.nome = novoNome; // Atualiza o nome da sala
                renderSalas(); // Re-renderiza as salas para refletir a alteração
                closeModalEdit(); // Fecha o modal após a edição
            } else {
                alert("Por favor, preencha o nome da sala.");
            }
        });
    }
}

// Função para fechar o modal de edição
function closeModalEdit() {
    document.getElementById('edit-room-modal').classList.remove('show');
}

// Cancelar a edição
document.getElementById('cancel-edit').addEventListener('click', function() {
    closeModalEdit();
});

// Função para excluir uma sala
function excluirSala(event) {
    const idSala = event.target.getAttribute('data-id');
    const salaIndex = salas.findIndex(s => s.id == idSala);

    if (salaIndex !== -1) {
        if (confirm("Tem certeza que deseja excluir esta sala?")) {
            salas.splice(salaIndex, 1); // Remove a sala da lista
            alert("Sala excluída com sucesso!"); // Feedback para o usuário
            renderSalas(); // Re-renderiza as salas para refletir a exclusão
        }
    }
}

// Inicializa o dashboard e renderiza as salas
document.addEventListener('DOMContentLoaded', () => {
    renderSalas();

    // Adicionar funcionalidade ao botão de criar sala
    const criarButton = document.querySelector('.action-btn');
    criarButton.addEventListener('click', criarSala);

    // Adicionar funcionalidade ao botão de cancelamento
    const cancelRoomButton = document.getElementById('cancel-room');
    cancelRoomButton.addEventListener('click', cancelCreateRoom);

    // Adicionar funcionalidade ao botão de submit
    const submitRoomButton = document.getElementById('submit-room');
    submitRoomButton.addEventListener('click', submitCreateRoom);

    // Adicionar funcionalidade ao botão de fechar o modal de criação
    const closeCreateButton = document.getElementById('close-create-modal');
    closeCreateButton.addEventListener('click', function() {
        const modal = document.getElementById('create-room-modal');
        modal.style.display = 'none';
    });

    // Adicionar funcionalidade ao botão de fechar o modal de edição
    const closeEditButton = document.getElementById('close-edit-modal');
    closeEditButton.addEventListener('click', function() {
        const modal = document.getElementById('edit-room-modal');
        modal.style.display = 'none';
    });
});
