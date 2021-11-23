import react from "react";

const SelectedOrderShipping = (props) => {

    const shippingDetails = props.shippingDetailsProps
    return (
        <>
            <h1>Shipment details:</h1>
            <p>Name: {shippingDetails.name}</p>
            <p>Email: {shippingDetails.email}</p>
            <p>User id: {shippingDetails.userId} (0 = user was not logged in)</p>
            <p>Address: {shippingDetails.address}</p>
            <p>Postal code: {shippingDetails.postal}</p>
            <p>City: {shippingDetails.city}</p>
        </>
    )

}

export default SelectedOrderShipping