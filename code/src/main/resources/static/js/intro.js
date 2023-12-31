let intro = document.querySelector('.intro');
let loadingHeader = document.querySelector('.loading-header');
let loading = document.querySelector('.loading');

window.addEventListener('DOMContentLoaded', ()=>{
    setTimeout(()=>{
        loadingHeader.forEach((span, idx) => {
            setTimeout(()=>{
                loading.classList.add('active');
            }, (idx + 1) * 400)
        });

        setTimeout(()=>{
            loadingHeader.forEach((span, idx) => {
                setTimeout(()=>{
                    loading.classList.remove('active')
                    loading.classList.add('fade');
                }, (idx + 1) * 400)
            });
        }, 2000);

        setTimeout(() =>{
            intro.style.top = '-100vh';
        }, 2300)
    });
});