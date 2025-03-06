document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Evita o recarregamento da página

    let email = document.getElementById("email").value;
    let senha = document.getElementById("senha").value;
    let mensagemErro = document.getElementById("mensagemErro");

    const usuariosValidos = [
        { email: "admin@exemplo.com", senha: "123456", role: "admin" },
        { email: "user@exemplo.com", senha: "123456", role: "user" }
    ];

    const usuario = usuariosValidos.find(u => u.email === email && u.senha === senha);

    if (usuario) {
        mensagemErro.textContent = "";

    
        const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mZXNzb3JAZW1haWwuY29tIiwicm9sZXMiOlsiUk9MRV9QUk9GRVNTT1IiXSwiaWF0IjoxNzQxMjAzNTUwLCJleHAiOjE3NDEyODk5NTB9.RZ4UmFt41VcFavAsY_L_Rwsinas3-6EM2AcYl9IUHRk";

        localStorage.setItem("token", token);
        localStorage.setItem("role", usuario.role);

        alert("Login bem-sucedido!");
        window.location.href = "dashboard/dashboard.html"; 
    } else {
        mensagemErro.textContent = "Email ou senha incorretos!";
    }
});

const darkModeStatus = localStorage.getItem('darkMode');
if (darkModeStatus === 'enabled') {
    document.body.classList.add('dark-mode');
} else {
    document.body.classList.remove('dark-mode');
}


const darkModeToggle = document.getElementById('darkModeToggle');
darkModeToggle.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode');

    
    if (document.body.classList.contains('dark-mode')) {
        darkModeToggle.innerHTML = '&#9788;'; 
        
        localStorage.setItem('darkMode', 'enabled');
    } else {
        darkModeToggle.innerHTML = '&#9789;'; 
       
        localStorage.setItem('darkMode', 'disabled');
    }
});
