$(document).ready(() => {
    const id = new URL(document.URL).searchParams.get("uid");
    $.ajax({
        url: `/rest/car/${id}`,
        method: "GET",
        success: cars => {
            fillInputs(cars);
        },
        error: err => {
            const errorObj = err.responseJSON;
            alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
        }
    })
});

function fillInputs(data) {
    $("#type-update").val(data.type);
    $("#color-update").val(data.color);
}

$("#update-car-submit").on("click", () => {
    const id = new URL(document.URL).searchParams.get("uid");
    $.ajax({
        url: `/rest/car/update/${id}`,
        method: "PUT",
        data: JSON.stringify(newCarObj()),
        contentType: "application/json",
        success: response => {
            alert(`SUCCESS: ${response}`);
            window.location.href = "/";
        },
        error: err => {
            const errorObj = err.responseJSON;
            alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
        }
    });
});

const newCarObj = () => {
    return {
        type: $("#type-update").val(),
        color: $("#color-update").val(),
    };
};