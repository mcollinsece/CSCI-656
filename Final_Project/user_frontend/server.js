console.log("Starting server...");
const express = require('express');
const app = express();
const port = 3000;

console.log("Express app created...");

// Serve static files from the same directory as this script
app.use(express.static('.'));

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}/`);
});
