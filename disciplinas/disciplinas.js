document.addEventListener("DOMContentLoaded", () => {
    const lista = document.getElementById("disciplinaLista");
    const modal = document.getElementById("modal");
    const modalTitulo = document.getElementById("modalTitulo");
    const btnCriar = document.getElementById("btnCriar");
    const salvarBtn = document.getElementById("salvarBtn");
    const cancelarBtn = document.getElementById("cancelarBtn");
    const closeBtn = document.querySelector(".close");

    let disciplinas = [
        { id: 1, nome: "Matemática Avançada", codigoTurma: "MAT101", nomeProfessor: "Carlos Silva" }
    ];

    function renderizarLista() {
        lista.innerHTML = "";
        disciplinas.forEach(disciplina => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td class="hidden">${disciplina.id}</td>
                <td>${disciplina.nome}</td>
                <td>${disciplina.codigoTurma}</td>
                <td>${disciplina.nomeProfessor}</td>
                <td>
                    <button class="editar" data-id="${disciplina.id}">✏️</button>
                    <button class="excluir" data-id="${disciplina.id}">🗑️</button>
                </td>
            `;
            lista.appendChild(row);
        });

        document.querySelectorAll(".editar").forEach(btn => {
            btn.addEventListener("click", (e) => {
                const id = parseInt(e.target.getAttribute("data-id"));
                editarDisciplina(id);
            });
        });

        document.querySelectorAll(".excluir").forEach(btn => {
            btn.addEventListener("click", (e) => {
                const id = parseInt(e.target.getAttribute("data-id"));
                excluirDisciplina(id);
            });
        });
    }

    function abrirModal() {
        modal.style.display = "block";
    }

    function fecharModal() {
        modal.style.display = "none";
        document.getElementById("disciplinaId").value = "";
        document.getElementById("nome").value = "";
        document.getElementById("codigoTurma").value = "";
        document.getElementById("nomeProfessor").value = "";
    }

    btnCriar.addEventListener("click", () => {
        modalTitulo.textContent = "Criar Disciplina";
        abrirModal();
    });

    closeBtn.addEventListener("click", fecharModal);

    cancelarBtn.addEventListener("click", fecharModal);  // Adicionado para o botão Cancelar

    salvarBtn.addEventListener("click", () => {
        const id = document.getElementById("disciplinaId").value;
        const nome = document.getElementById("nome").value;
        const codigoTurma = document.getElementById("codigoTurma").value;
        const nomeProfessor = document.getElementById("nomeProfessor").value;

        if (nome && codigoTurma && nomeProfessor) {
            if (id) {
                const index = disciplinas.findIndex(d => d.id == id);
                disciplinas[index] = { id: parseInt(id), nome, codigoTurma, nomeProfessor };
            } else {
                disciplinas.push({ id: disciplinas.length + 1, nome, codigoTurma, nomeProfessor });
            }
            renderizarLista();
            fecharModal();
        } else {
            alert("Preencha todos os campos!");
        }
    });

    function editarDisciplina(id) {
        const disciplina = disciplinas.find(d => d.id === id);
        if (disciplina) {
            modalTitulo.textContent = "Editar Disciplina";
            document.getElementById("disciplinaId").value = disciplina.id;
            document.getElementById("nome").value = disciplina.nome;
            document.getElementById("codigoTurma").value = disciplina.codigoTurma;
            document.getElementById("nomeProfessor").value = disciplina.nomeProfessor;
            abrirModal();
        }
    }

    function excluirDisciplina(id) {
        disciplinas = disciplinas.filter(d => d.id !== id);
        renderizarLista();
    }

    renderizarLista();
});
