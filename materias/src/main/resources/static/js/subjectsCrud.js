//LIST
const $table = document.getElementById("data");
function list() {
    fetch("/subjects/readAll")
        .then(r => r.json())
        .then(r => {
            $table.innerHTML="";
            r.data.forEach(i => {
                let professorName = "Sin Profesor";
                if (i.professor != null) {
                    professorName = i.professor.firstName + ", " + i.professor.lastName;
                }

                $table.innerHTML += `
                <tr>
                    <th>${i.id}</th>
                    <th>${i.name}</th>
                    <th>${i.bachiller?.name ?? "Sin Bachillerato"}</th>
                    <th>${professorName}</th>
                    <th>
                        <button class="btn-edit" onclick="edit(${i.id})">
                            <i class="fa-solid fa-pen-to-square"></i></button>
                        <button class="btn-delete" onclick="del(${i.id})">
                            <i class="fa-solid fa-trash-can"></i></button>
                    </th>
                </tr>
                `
            })
        })
        .catch(e => console.error("Error: ", e));
}
document.addEventListener("DOMContentLoaded", list);

//FORM
const $formHolder = document.getElementById("form-holder");

function registry() {
    openForm(`/subjects/form/create`);
}

function edit(id) {
    openForm(`/subjects/form/update/${id}`);
}

function openForm(mapping) {
    fetch(mapping)
        .then(r => r.text())
        .then(formHTML => {
            $formHolder.innerHTML = formHTML;
            $formHolder.addEventListener('submit', async function(evt) {
                evt.preventDefault();
                const $form = evt.target;
                const formData = new FormData($form);

                const r = await fetch($form.action, {
                    method: $form.method,
                    body: formData
                });
                if (!r.ok) {
                    const e = await r.text();
                    alert(`Error: ${e}`);
                    return;
                }

                closeForm();
                list();
            })
        })
        .catch(e => console.error("Error: ", e));
}

function closeForm() {
    $formHolder.innerHTML = "";
}

//DELETE
function del(id) {
    fetch(`/subjects/delete/${id}`, { method: "DELETE" })
        .then(r => {
            list();
        })
        .catch(e => console.error("Error: ", e));
}