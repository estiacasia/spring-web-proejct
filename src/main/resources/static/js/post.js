const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let postId = document.getElementById('post-id').value;
        fetch(`/api/posts/${postId}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('Completed delete.');
                location.replace('/posts');
            });
    });
}