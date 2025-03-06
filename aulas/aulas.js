// Lista de aulas (simulação)
let aulas = [
    { id: 1, disciplina: "Matemática", sala: "101", diaSemana: "Segunda", horarioInicio: "08:00", duracao: 120 },
    { id: 2, disciplina: "História", sala: "102", diaSemana: "Quarta", horarioInicio: "10:30", duracao: 90 }
];

let aulaEditando = null; // Armazena a aula que está sendo editada

// Renderizar aulas na tela
function renderAulas() {
    const section = document.querySelector('.section-aulas');
    section.innerHTML = "";

    aulas.forEach(aula => {
        const aulaCard = document.createElement('div');
        aulaCard.classList.add('room-card');
        aulaCard.innerHTML = `
            <span><strong>Disciplina:</strong> ${aula.disciplina}</span>
            <span><strong>Sala:</strong> ${aula.sala}</span>
            <span><strong>Dia:</strong> ${aula.diaSemana}</span>
            <span><strong>Início:</strong> ${aula.horarioInicio}</span>
            <span><strong>Duração:</strong> ${aula.duracao} min</span>
            <button class="edit-btn" data-id="${aula.id}">Editar</button>
            <button class="delete-btn" data-id="${aula.id}">Excluir</button>
        `;
        section.appendChild(aulaCard);
    });

    document.querySelectorAll('.edit-btn').forEach(button => button.addEventListener('click', abrirModalEdicao));
    document.querySelectorAll('.delete-btn').forEach(button => button.addEventListener('click', excluirAula));
}

// Abrir modal para criar uma nova aula
document.getElementById("open-modal-aula").addEventListener("click", () => {
    aulaEditando = null;
    document.getElementById("form-aula").reset();
    document.getElementById("modal-aula").style.display = "flex";
});

// Fechar modal
document.getElementById("close-modal-aula").addEventListener("click", fecharModal);
document.getElementById("cancel-aula").addEventListener("click", fecharModal);

function fecharModal() {
    document.getElementById("modal-aula").style.display = "none";
}

function abrirModalEdicao(event) {
    const idAula = event.target.getAttribute('data-id');
    aulaEditando = aulas.find(a => a.id == idAula);

    if (aulaEditando) {
        document.getElementById("disciplina").value = aulaEditando.disciplina;
        document.getElementById("sala").value = aulaEditando.sala;
        document.getElementById("diaSemana").value = aulaEditando.diaSemana;
        document.getElementById("horarioInicio").value = aulaEditando.horarioInicio;
        document.getElementById("duracao").value = aulaEditando.duracao;

        // Altera o título para "Editar Aula"
        document.getElementById("modal-title").textContent = "Editar Aula";

        document.getElementById("modal-aula").style.display = "flex";
    }
}

// Altera o título para "Criar Aula" quando abrir o modal normalmente
document.getElementById("open-modal-aula").addEventListener("click", () => {
    aulaEditando = null;
    document.getElementById("form-aula").reset();
    document.getElementById("modal-title").textContent = "Criar Aula";
    document.getElementById("modal-aula").style.display = "flex";
});

// Criar ou editar aula
document.getElementById("submit-aula").addEventListener("click", () => {
    const disciplina = document.getElementById("disciplina").value;
    const sala = document.getElementById("sala").value;
    const diaSemana = document.getElementById("diaSemana").value;
    const horarioInicio = document.getElementById("horarioInicio").value;
    const duracao = document.getElementById("duracao").value;

    if (disciplina && sala && diaSemana && horarioInicio && duracao) {
        if (aulaEditando) {
            // Atualizar aula existente
            aulaEditando.disciplina = disciplina;
            aulaEditando.sala = sala;
            aulaEditando.diaSemana = diaSemana;
            aulaEditando.horarioInicio = horarioInicio;
            aulaEditando.duracao = parseInt(duracao);
        } else {
            // Criar nova aula
            const novaAula = {
                id: aulas.length + 1,
                disciplina,
                sala,
                diaSemana,
                horarioInicio,
                duracao: parseInt(duracao)
            };

            aulas.push(novaAula);
        }

        renderAulas();
        fecharModal();
    } else {
        alert("Preencha todos os campos!");
    }
});

// Excluir aula
function excluirAula(event) {
    const idAula = event.target.getAttribute('data-id');
    aulas = aulas.filter(a => a.id != idAula);
    renderAulas();
}

// Inicializar a página
document.addEventListener('DOMContentLoaded', renderAulas);
