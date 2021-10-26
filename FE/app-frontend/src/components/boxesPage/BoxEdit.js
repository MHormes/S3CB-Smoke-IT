import React, { useState } from 'react'
import { useHistory } from 'react-router'
import axios from 'axios'
import * as urls from "./../../URL";

//component that will show during the box edit process
const BoxEdit = (props) => {

    const history = useHistory()
    const boxToEdit = props.boxToEditProps

    const [boxDetails, setBoxDetails] = useState(
        {
            name: boxToEdit.name,
            basePrice: boxToEdit.basePrice,
            content: boxToEdit.content,
            description: boxToEdit.description
        }
    )

    //method to call edit endpoint
    const editBoxInBE = (id) => {
        const boxDTO = {
            id: id,
            name: boxDetails.name,
            basePrice: boxDetails.basePrice,
            content: boxDetails.content,
            description: boxDetails.description
        }
        axios.put(urls.baseURL + urls.boxesEditURL, boxDTO).then(res => {
            if (res.status === 200) {
                console.log("Update successfull")
            }
        }).catch(err => {
            alert(err.response.data)
        })
    }



    const onChange = e => {
        setBoxDetails({
            ...boxDetails,
            [e.target.name]: e.target.value,
        })
    }

    const handleSubmit = e => {
        e.preventDefault();
        if (boxDetails.name.trim() && boxDetails.content.trim() && boxDetails.description.trim()) {
            editBoxInBE(boxToEdit.id);
            history.push("/boxes")
        }
        else {
            alert("Please fill in all fields")
        }
    }
    return (
        <form onSubmit={handleSubmit}>
            <h1>Edit an existing box</h1>
            <label>
                Name:
                <input
                    type="text"
                    name="name"
                    placeholder="Insert a name"
                    value={boxDetails.name}
                    onChange={onChange} />
            </label>
            <br />
            <label>
                Base price:
                <br />
                <input
                    type="number"
                    name="basePrice"
                    placeholder="Insert a base price"
                    value={boxDetails.basePrice}
                    onChange={onChange} />
            </label>
            <br />
            <label>
                Content:
                <input
                    type="text"
                    name="content"
                    placeholder="Insert the content"
                    value={boxDetails.content}
                    onChange={onChange} />
            </label>
            <br />
            <label>
                Description:
                <input
                    type="text"
                    name="description"
                    placeholder="Insert a description"
                    value={boxDetails.description}
                    onChange={onChange} />
            </label>
            <br />
            <input type="submit" value="Submit" />
        </form>
    )
}

export default BoxEdit