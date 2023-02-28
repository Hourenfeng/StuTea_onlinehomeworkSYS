<template>
    <div class="global-loading-box">
        <div class="view-login">
            
            <div class="content">
                <div class="left">
                    <img  src="../../public/img/newbg.png">
                </div>
                <div class="right">
                    <slot></slot>
                </div>
            </div>
            
        </div>
    </div>
</template>

<script>
    export default {
        name: "loginAndRegistMain",
        components: {},
        methods: {
            
        }
    }
</script>

<style scoped>
    .global-loading-box {
        z-index: 0;
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        transform: scale(1);
        background: transparent;
        transition: transform .3s ease-out 299ms,background .3s ease-in-out 0ms;
    }
    .view-login {
        position: relative;
        width: 100%;
        min-height: 100vh;
        background: #f8f9fa;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
    }
    .logo-box {
        position: absolute;
        top: 48px;
        left: 62px;
    }
    .content {
        display: flex;
        justify-content: space-between;
    }
    .left {
        margin-right: -100px;
    }
</style>