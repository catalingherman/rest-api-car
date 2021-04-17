$(document).ready(() => {
    getAllCars();
});

function getAllCars() {
    $.ajax({
        url: "/rest/car/",
        method: "GET",
        success: response => {
            displayCars(response);
        },
        error: err => {
            let responseObj = err.responseJSON;
            alert(`ERROR: " ${responseObj.message} " TIME ${responseObj.time}`);
        }
    })
}

function displayCars(cars) {
    if (cars.length>0) {
        let placeholder = "";
        $.each(cars, (index, car) => {
            placeholder += 
            `<tr>
                <input class='car-id' type='hidden' value='${car.id}'>
                <td>${(index+1)}</td>;
                <td>${car.color} ${car.type}</td>
                <td><button class='update-car'>Update</button></td>
                <td><button class='delete-car'>Delete</button></td>
            </tr>`;
        });
        $("#cars-placeholder tbody").html(placeholder);
    } else {
        $("#cars-div").html("<p>No cars in the system.</p>");
    }
}

$("#add").on("click", () => {
    window.location.href = "/add";
});

$("#cars-placeholder").on("click", ".update-car", () => {
    let id = $(".car-id").val();
    window.location.href = `/update?uid=${id}`;
});

$("#cars-placeholder").on("click", ".delete-car", () => {
    if(confirm("Click okay to confirm car delete request")) {
        let id = $(".car-id").val();
        $.ajax({
            url: `/rest/car/delete/${id}`,
            method: "DELETE",
            success: message => {
                alert(`SUCCESS: ${message}`);
                getAllCars();
            },
            error: err => {
                let responseObj = err.responseJSON;
                alert(`ERROR: "${responseObj.message}" TIME: ${responseObj.time}`);
            }
        });
    }
});
