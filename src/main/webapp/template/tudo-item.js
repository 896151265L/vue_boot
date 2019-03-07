Vue.component('todo', {
    props: ['data'],
    template: '<p">{{data.name}}</p>',
    created() {
        console.log('created')
    }
})