const onSelectEmployee = (selectedEmployee) => {
    console.log("selected from parameter, ", selectedEmployee.value);

    fetchMeetingsForEmployee(selectedEmployee)

}

const fetchMeetingsForEmployee = (employee) => {
    // for the selectedEmployee hidden field
    //document.getElementById('selectedEmployee').value = employee.value;
    var meetings = null;

    fetch('RetrieveMeetingsForEmployee',
            {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                //body: JSON.stringify({
                //    employeeName: employee.value,
                //})
                body: employee.value
            })
            .then(
                    function (response) {
                        response.json()
                                .then(
                                        function (data) {
                                            //alert(JSON.stringify(data.length));
                                            jsonString = JSON.stringify(data);
                                            setupEmployeeSelect(data);
                                        })
                    });
}

const setupEmployeeSelect = (json) => {
    //console.log(JSON.stringify(meetings));
    let jsonString = JSON.stringify(json);
    console.log(jsonString);
    let meetingSelect = "";
    /*meetingSelect = meetingSelect.concat(
     //"<select id=\"meetingSelect\" onchange=\"submit()\">");
     "<select id=\"meetingSelect\" onchange=\"onSelectMeeting()\">");
     
     */
    for (let i = 0; i < json.length; i++)
    {
        meetingSelect = meetingSelect.concat(
                "<option value=",
                json[i].id,
                ">",
                //"Here we will have some meetings for ",
                //selectedEmployee.value,
                //json.length,
                "Meeting id=",
                json[i].id,
                "</option>"
                )
    }
    console.log(meetingSelect);
    document.getElementById("meetingSelect").innerHTML = meetingSelect;
}



/*const onSelectMeeting = () => {
    console.log("onSelectMeeting: ")
    alert('onSelectMeeting');
}
*/