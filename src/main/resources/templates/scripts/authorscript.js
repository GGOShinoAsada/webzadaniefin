const app = new Vue({
    el:'#app',
    data:{
        errors:[],
        name:null,
        country:null
    },
    methods:{
        checkForm:function(e) {
            if(this.name && this.country) return true;
            this.errors = [];
            if(!this.name) this.errors.push("Name required.");
            if(!this.country) this.errors.push("country required.");
            e.preventDefault();
        }
    }
})