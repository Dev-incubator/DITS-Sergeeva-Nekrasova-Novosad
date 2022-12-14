
const testThemeSelect = document.getElementById("testThemeSelect");
const testDescription = document.getElementById("testDescription");
const startTestButton = document.getElementById("startTestButton");
const testSelect = document.getElementById("testSelect");

const baseUrl = window.origin;

let testsData = null;


document.addEventListener("DOMContentLoaded", () => {
    const selectedOption = testThemeSelect.querySelector("option:checked");
    const value = selectedOption.textContent;
    console.log(value);
    updateCurrentThemeData(value).then();
});

testThemeSelect.addEventListener("change", e => {
    const selectedOption = e.target.querySelector("option:checked");
    const value = selectedOption.textContent;
    console.log(value);
    updateCurrentThemeData(value).then();
});

async function updateCurrentThemeData(themeName) {
    let url = new URL(baseUrl + "/user/chooseTheme");
    url.search = new URLSearchParams({ theme : themeName }).toString();

    const response = await fetch(url.toString());
    testsData = await response.json();
    updateTestsData();
}

function updateTestsData() {
    testSelect.querySelectorAll("option")
        .forEach(e => e.remove());
    testsData.forEach((e, i) => {
        const option = document.createElement("option");
        option.setAttribute("value", e.testId);
        option.dataset.index = i;
        option.value = e.testId;
        option.innerText = e.name;
        
        testSelect.append(option);
    });

    testSelect.closest("div").classList.remove("hidden");
    startTestButton.closest("div").classList.remove("hidden");

    updateDescription();
}

testSelect.addEventListener("change", updateDescription)

function updateDescription() {
    const selectedOption = Array.from(testSelect.querySelectorAll("option")).filter(o => o.selected === true)[0];

    if (selectedOption != null) {
        const testOptionIndex = selectedOption.dataset.index;
        testDescription.innerText = testsData[Number(testOptionIndex)].description;
    } else {
        testDescription.innerText = "";
    }
}