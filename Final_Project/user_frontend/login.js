document.getElementById('loginForm').addEventListener('submit', function(event){
    event.preventDefault();

    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    fetch('/api/auth/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
    .then(response => response.json())
    .then(data => {
        if(data.accessToken){
            localStorage.setItem('bearerToken', data.accessToken); // Storing the token in localStorage
            console.log(data.accessToken);
            localStorage.setItem('username', username); // Storing the username in localStorage
            window.location.href = '/voting.html'; // Redirecting to the voting page
        } else {
            alert('Login failed!');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
});
