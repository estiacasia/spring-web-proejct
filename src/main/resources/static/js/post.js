const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let postId = document.getElementById('post-id').value;
        fetch(`/api/posts/${postId}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('Post deletion completed.');
                location.replace('/posts');
            });
    });
}

const modifyButton = document.getElementById('modify-btn');
if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('postId');

        fetch('/api/posts/' + id, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value,
            })
        })
            .then((response) => {
                alert('Post modification completed.');
                location.replace('/posts/' + id);
            });
    });
}