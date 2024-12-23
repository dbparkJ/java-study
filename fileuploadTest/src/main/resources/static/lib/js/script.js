document.addEventListener('DOMContentLoaded', function () {
    var uploadButton = document.getElementById('upload-button');
    var fileInput = document.getElementById('file-input');
    var progressBar = document.getElementById('upload-progress');
    var uploadPercentage = document.getElementById('upload-percentage');

    uploadButton.addEventListener('click', function () {
        var file = fileInput.files[0];
        if (!file) {
            alert('Please choose a file to upload');
            return;
        }

        // Create a new tus upload
        var upload = new tus.Upload(file, {
            endpoint: "http://211.48.125.135:38080/files", // Replace with your tus server endpoint
            retryDelays: [0, 3000, 5000, 10000, 20000],
            metadata: {
                filename: file.name,
                filetype: file.type
            },
            onError: function (error) {
                console.error("Failed because: " + error);
                alert("Error: " + error);
            },
            onProgress: function (bytesUploaded, bytesTotal) {
                var percentage = (bytesUploaded / bytesTotal * 100).toFixed(2);
                progressBar.value = percentage;
                uploadPercentage.textContent = percentage + '%';
            },
            onSuccess: function () {
                console.log("Download %s from %s", upload.file.name, upload.url);
                alert("Upload Complete!");
            }
        });
        upload.findPreviousUploads().then(function (previousUploads) {
            // Found previous uploads so we select the first one.
            if (previousUploads.length) {
                upload.resumeFromPreviousUpload(previousUploads[0])
            }

            // Start the upload
            upload.start()
        })
    });
});
