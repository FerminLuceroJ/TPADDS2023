const dropZone = document.getElementById('drop-zone');
const fileList = document.querySelector('#file-list ul');

dropZone.addEventListener('dragover', (e) => {
    e.preventDefault();
    dropZone.classList.add('highlight');
});

dropZone.addEventListener('dragleave', () => {
    dropZone.classList.remove('highlight');
});

dropZone.addEventListener('drop', (e) => {
    e.preventDefault();
    dropZone.classList.remove('highlight');
    const files = e.dataTransfer.files;
    handleFiles(files);
});

const fileInput = document.getElementById('file-input');
fileInput.addEventListener('change', () => {
    const files = fileInput.files;
    handleFiles(files);
});

function handleFiles(files) {
    for (const file of files) {
        const listItem = document.createElement('li');
        listItem.textContent = `${file.name} (${formatFileSize(file.size)})`;
        fileList.appendChild(listItem);
    }
}

function formatFileSize(bytes) {
    if (bytes === 0) return '0 Bytes';

    const k = 1024;
    const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));

    return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i];
}


async function sendFiles(files) {
    const formData = new FormData();
    
    for (const file of files) {
        formData.append('files', file);
    }
    
    try {
        const response = await fetch('/upload', {
            method: 'POST',
            body: formData,
        });

        if (response.ok) {
            console.log('Files uploaded successfully.');
            fileList.innerHTML = ''; // Clear the file list
        } else {
            console.error('File upload failed.');
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

// Event listeners (drag and drop, file input) remain the same

// Function to handle file drop or input change
function handleFiles(files) {
    for (const file of files) {
        const listItem = document.createElement('li');
        listItem.textContent = `${file.name} (${formatFileSize(file.size)})`;
        fileList.appendChild(listItem);
    }
    
    // Send files to the server when selected or dropped
    sendFiles(files);
}