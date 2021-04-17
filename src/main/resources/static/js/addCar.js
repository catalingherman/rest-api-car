$("#add-car-submit").on("click", () => {
    if (validInput() === true) {
        $.ajax({
            method: "POST",
            url: "/rest/car/add",
            data: JSON.stringify(carObject()),
            contentType: "application/json",
            success: response => {
                alert(`SUCCESS: ${response}`);
                window.location.href = "/";
            },
            error: err => {
                let errorObj = err.responseJSON;
                alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
            }
        });
    } else {
        alert("I am not making the call because of invalid input");
    }
});

const carObject = () => {
    return {
        type: $("#type").val(),
        color: $("#color").val(),
    };
};

const validInput = () => {
    if ($("#type").val() && $("#color").val())
        return true;
    else
        return false;
};