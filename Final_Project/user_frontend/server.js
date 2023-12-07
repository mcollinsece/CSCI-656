console.log("Starting server...");
const express = require('express');
const app = express();
const port = 81;

console.log("Express app created...");

// Serve static files from the same directory as this script
app.use(express.static('.'));

app.listen(port, '0.0.0.0', () => {
    console.log(`Server running at http://0.0.0.0:${port}/`);
});
