import {json} from "react-router-dom";


export const lessonsLoader = async ({params, authData}) => {
    console.log(`fetching api data from course ${params.course} using uuid ${authData.userUuid}}`);
    //API REQUEST
    // const resp = CourseService.getAll({})
    const response = json(
        [
            {
                "uuid": "f78105fe-350b-4e13-8058-335d55c89a1a",
                "title": "Математический Анализ",
                "description": "что-то важное",
                "serialNumber": 1,
                "groupUuid": "f78105fe-350b-4e13-8058-335d55c89a1a"
            },
            {
                "uuid": "f78105fe-350b-4e13-8058-335d55c89a2a",
                "title": "Математический Анализ",
                "description": "что-то важное",
                "serialNumber": 2,
                "groupUuid": "f78105fe-350b-4e13-8058-335d55c89a1a"
            },
            {
                "uuid": "f78105fe-350b-4e13-8058-335d55c89a3a",
                "title": "Математический Анализ",
                "description": "что-то важное",
                "serialNumber": 3,
                "groupUuid": "f78105fe-350b-4e13-8058-335d55c89a1a"
            },
            {
                "uuid": "f78105fe-350b-4e13-8058-335d55c89a4a",
                "title": "Математический Анализ",
                "description": "что-то важное",
                "serialNumber": 3,
                "groupUuid": "f78105fe-350b-4e13-8058-335d55c89a1a"
            },
        ], {status: 200}
    );
    if (response.status === 200) {
        return response
    } else {
        throw new Response("Not Found", { status: response.status });
    }
}