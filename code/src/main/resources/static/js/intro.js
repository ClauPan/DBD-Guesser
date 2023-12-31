let intro = document.querySelector('.intro');
let loadingHeader = document.querySelector('.loading-header');
let loading = document.querySelector('.loading');

window.addEventListener('DOMContentLoaded', ()=>{
    setTimeout(()=>{
        loading.forEach((span, idx) => {
            setTimeout(()=>{
                span.classList.add('active');
            }, (idx + 1) * 400)
        });

        setTimeout(()=>{
            loading.forEach((span, idx) => {
                setTimeout(()=>{
                    span.classList.remove('active')
                    span.classList.add('fade');
                }, (idx + 1) * 400)
            });
        }, 2000);

        setTimeout(() =>{
            intro.style.top = '-100vh';
        }, 2300)
    });
});