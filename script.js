document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita o recarregamento da página

    let email = document.getElementById("email").value;
    let senha = document.getElementById("senha").value;
    let mensagemErro = document.getElementById("mensagemErro");

    // Simulação de usuários válidos (Você pode conectar com uma API depois)
    const usuariosValidos = [
        { email: "admin@exemplo.com", senha: "123456", role: "admin" },
        { email: "user@exemplo.com", senha: "123456", role: "user" }
    ];

    // Procura o usuário com o email e senha fornecidos
    const usuario = usuariosValidos.find(u => u.email === email && u.senha === senha);

    if (usuario) {
        mensagemErro.textContent = ""; // Limpa a mensagem de erro

        // Simula a geração de um token (por exemplo, JWT)
        const token = "fake-jwt-token-123456";

        // Simula o armazenamento do token e role
        localStorage.setItem("token", token);
        localStorage.setItem("role", usuario.role);

        alert("Login bem-sucedido!");
        window.location.href = "dashboard/dashboard.html"; // Redireciona para o dashboard
    } else {
        mensagemErro.textContent = "Email ou senha incorretos!";
    }
});

// Verificar o status do modo escuro no localStorage
const darkModeStatus = localStorage.getItem('darkMode');
if (darkModeStatus === 'enabled') {
    document.body.classList.add('dark-mode');
} else {
    document.body.classList.remove('dark-mode');
}

// Função para alternar o modo
const darkModeToggle = document.getElementById('darkModeToggle');
darkModeToggle.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode');

    // Altera o ícone para lua quando o modo escuro é ativado
    if (document.body.classList.contains('dark-mode')) {
        darkModeToggle.innerHTML = '&#9788;'; // Ícone de lua
        // Armazena a preferência de modo escuro no localStorage
        localStorage.setItem('darkMode', 'enabled');
    } else {
        darkModeToggle.innerHTML = '&#9789;'; // Ícone de sol
        // Armazena a preferência de modo claro no localStorage
        localStorage.setItem('darkMode', 'disabled');
    }
});
