import React, { useState } from 'react'
import { useHistory } from 'react-router'
import axios from 'axios'
import * as urls from "./../../URL";

//component shown when adding a new box
const BoxAdd = () => {

    const history = useHistory()

    //method to add new box to the BE
    const addBoxInBE = (box, file) => {
        file.append("name", box.name);
        file.append("basePrice", box.basePrice)
        file.append("content", box.content)
        file.append("description", box.description)
        
        axios.post(urls.baseURL + urls.boxesAddURL, file)
    }

    //State for boxdetails
    const [boxDetails, setBoxDetails] = useState({
        name: "",
        basePrice: 0,
        content: "",
        description: "",
    })

    //State for picture upload
    const [selectedFile, setSelectedFile] = useState(null);

    //on change for the text fields
    const onChange = e => {
        setBoxDetails({
            ...boxDetails,
            [e.target.name]: e.target.value,
        })
    }

    //submit for the form
    const handleSubmit = e => {
        e.preventDefault()

        let formData = new FormData()
        if (selectedFile != null) {
            formData.append(
                'imageFile',
                selectedFile
            )
        } else {
            alert("Please supply an image");
            return;
        }

        if (boxDetails.name.trim() && boxDetails.content.trim() && boxDetails.description.trim() && boxDetails.basePrice >= 0.00) {
            addBoxInBE(boxDetails, formData);

            //clear the state
            setBoxDetails({
                name: "",
                basePrice: 0,
                content: "",
                description: "",
            })

            //clear file state
            setSelectedFile(null)

            //redirect to boxes page (refreshes)
            history.push("/boxes")
        }
        else {
            alert("Please fill in all fields")
        }
    }

    const onFilechange = e => {
        setSelectedFile(e.target.files[0])
    }

    return (
        <form onSubmit={handleSubmit}>
            <h1>Add a new box</h1>
            <label>
                Upload a picture:
                <br />
                <input
                    type="file"
                    accept="image/png, image/jpg, image/jpeg"
                    onChange={onFilechange}
                />
            </label>
            <br />
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

export default BoxAdd