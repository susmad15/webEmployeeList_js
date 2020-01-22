console.log('selectCreator: Start');

class selectCreator {

    constructor(selectObject, url, data) {
        this.selectObject = selectObject;
        this.url = url;
        this.data = data;
    }

    create = (nothing) => {
        this.fetchData(this.selectObject);
    }

    fetchData = (selectObject) => {
        fetch(this.url,
                {
                    method: 'post',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: this.data
                })
                .then(
                        (response) => {
                    response.json()
                            .then(
                                    data => {

                                        this.setupSelect(data);
                                    })
                });
    }
    ;
            setupSelect = (json) => {

        let jsonString = JSON.stringify(json);
        console.log(jsonString);

        let selectString = "";

        for (let i = 0; i < json.length; i++)
        {
            selectString += '<option value="' + json[i].id + '">' + 'Option ' + json[i].id + '</option>';

        }

        console.log(selectString);
        document.getElementById(this.selectObject.id).innerHTML = selectString;

    }

}