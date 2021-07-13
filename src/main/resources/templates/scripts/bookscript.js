const app = new Vue({
    el:'#app',
    data:{
        errors:[],
        name:null,
        isbn:null,
    },
    methods:{
        checkForm:function(e) {
            if(this.name && this.isbn) return true;
            this.errors = [];
            if(!this.name) this.errors.push("Name required.");
            if(!this.isbn) this.errors.push("ISBN required.");
            e.preventDefault();
        }
    }
})