const onSelectMeeting = (selectedMeeting) => {
    fetchParticipantsForMeeting(selectedMeeting)
}

const fetchParticipantsForMeeting = (meeting) => {

    var participants = null;

    fetch('RetrieveParticipantsForMeeting',
            {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },

                body: meeting.value
            })
            .then(
                    function (response) {
                        response.json()
                                .then(
                                        function (data) {
                                            //alert(JSON.stringify(data.length));
                                            jsonString = JSON.stringify(data);
                                            setupParticipantSelect(data);
                                        })
                    });
}

const setupParticipantSelect = (json) => {
    //console.log(JSON.stringify(meetings));
    let jsonString = JSON.stringify(json);
    console.log(jsonString);
    let participantSelect = "";

    for (let i = 0; i < json.length; i++)
    {
        participantSelect = participantSelect.concat(
                "<option value=",
                json[i].id,
                ">",
                //"Here we will have some meetings for ",
                //selectedEmployee.value,
                //json.length,
                "Participant name=",
                json[i].name,
                "</option>"
                )
    }
    console.log(participantSelect);
    document.getElementById("participantSelect").innerHTML = participantSelect;
}


