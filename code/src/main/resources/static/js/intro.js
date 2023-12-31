let intro = document.querySelector('.intro');
let loadingHeader = document.querySelector('.loading-header');
let loading = document.querySelector('.loading');

window.addEventListener('DOMContentLoaded', ()=>{
    setTimeout(()=>{
        setTimeout(()=>{
                loading.classList.add('active');
            }, 800);

        loading.classList.remove('active');

        setTimeout(() =>{
            setTimeout(()=>{
                loading.classList.add('fade');
            }, 800);
        }, 2000);

        setTimeout(() =>{
            if(intro) {
                intro.classList.add('fade');
            }
        }, 4000);
    });
});