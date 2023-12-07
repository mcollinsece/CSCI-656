const express = require('express');
const bodyParser = require('body-parser');
const axios = require('axios');
const path = require('path'); // Import the path module
const app = express();
const port = 82;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// // Serve the index.html file at the root URL
// app.get('/', (req, res) => {
//     res.sendFile(path.join(__dirname, 'index.html'));
// });
// Serve static files from the same directory as this script
app.use(express.static('.'));

// Route to handle POST request
app.post('/addUser', async (req, res) => {
    try {
        const { username, email, password, role } = req.body;
        const response = await axios.post('http://localhost:8080/api/auth/signup', {
            username,
            email,
            password,
            role: [role]
        }, {
            headers: {
                'Authorization': 'Bearer your_token_here', // Replace with actual token
                'Content-Type': 'application/json'
            }
        });

        res.status(200).send(response.data);
    } catch (err) {
        console.error(err);
        if (err.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            res.status(err.response.status).send(err.response.data);
        } else if (err.request) {
            // The request was made but no response was received
            res.status(500).send('No response from API');
        } else {
            // Something happened in setting up the request that triggered an Error
            res.status(500).send('Error in making the request');
        }
    }
});

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}/`);
});




// console.log("Starting server...");
// const express = require('express');
// const app = express();
// const port = 82;

// console.log("Express app created...");

// // Serve static files from the same directory as this script
// app.use(express.static('.'));

// app.listen(port, '0.0.0.0', () => {
//     console.log(`Server running at http://0.0.0.0:${port}/`);
// });



// console.log("Starting server...");
// const express = require('express');
// const app = express();
// const port = 82;
// const path = require('path'); // Require the path module

// console.log("Express app created...");

// // Optional: Serve static files from a subdirectory, if you have other static files
// // app.use(express.static('public'));

// // Define a route for /admin that serves index.html
// app.get('/admin', (req, res) => {
//     res.sendFile(path.join(__dirname, 'index.html'));
// });

// app.listen(port, () => {
//     console.log(`Server running at http://localhost:${port}/`);
// });
