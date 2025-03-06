// Alterna entre o modo claro e escuro ao clicar no ícone
const darkModeToggle = document.getElementById('darkModeToggle');

// Função para alternar o modo
darkModeToggle.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode');

    // Altera o ícone para lua quando o modo escuro é ativado
    if (document.body.classList.contains('dark-mode')) {
        darkModeToggle.innerHTML = '&#9788;'; // Ícone de lua
    } else {
        darkModeToggle.innerHTML = '&#9789;'; // Ícone de sol
    }

    // Salvar a preferência no localStorage para persistir entre as páginas
    if (document.body.classList.contains('dark-mode')) {
        localStorage.setItem('darkMode', 'true');
    } else {
        localStorage.setItem('darkMode', 'false');
    }
});

document.addEventListener("DOMContentLoaded", () => {
    // Pega a role armazenada no localStorage
    const roleUsuario = localStorage.getItem("role") || "Usuário"; // Exibe "Usuário" como padrão se não encontrar a role

    // Define o texto do span com o id 'usuarioNome' para a role
    document.getElementById("usuarioNome").textContent = roleUsuario;
});

// Verifica a preferência no localStorage ao carregar a página
if (localStorage.getItem('darkMode') === 'true') {
    document.body.classList.add('dark-mode');
    darkModeToggle.innerHTML = '&#9788;'; // Ícone de lua
} else {
    darkModeToggle.innerHTML = '&#9789;'; // Ícone de sol
}
