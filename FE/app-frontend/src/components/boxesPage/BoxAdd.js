import React, { useState } from 'react'
import { useHistory } from 'react-router'
import axios from 'axios'
import * as urls from "./../../URL";

//component shown when adding a new box
const BoxAdd = () => {

    const history = useHistory()

    //method to add new box to the BE
    const addBoxInBE = (box) => {
        const boxDTO =
        {
            name: box.name,
            basePrice: box.basePrice,
            content: box.content,
            description: box.description
        }

        axios.post(urls.baseURL+urls.boxesAddURL, boxDTO)
    }

    const [boxDetails, setBoxDetails] = useState({
        name: "",
        basePrice: 0,
        content: "",
        description: "",
    })

    const onChange = e => {
        setBoxDetails({
            ...boxDetails,
            [e.target.name]: e.target.value,
        })
    }

    const handleSubmit = e => {
        e.preventDefault();
        if (boxDetails.name.trim() && boxDetails.content.trim() && boxDetails.description.trim()) {
            addBoxInBE(boxDetails);

            //clear the state
            setBoxDetails({
                name: "",
                basePrice: 0,
                content: "",
                description: "",
            })

            //redirect to boxes page (refreshes)
            history.push("/boxes")
        }
        else {
            alert("Please fill in all fields")
        }
    }




    return (
        <form onSubmit={handleSubmit}>
            <h1>Add a new box</h1>
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
                <input
                    type="text"
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

export default BoxAdd