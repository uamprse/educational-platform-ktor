import {json} from "react-router-dom";


export const coursesLoader = async ({authData}) => {
    console.log(`fetching api data for courses using uuid ${authData.userUuid}}`);
    //API REQUEST
    // const resp = CourseService.getAll({})
    const response = json(
        [
            {
                "uuid": "f78105fe-350b-4e13-8058-335d55c89a1a",
                "title": "Математический Анализ 1",
                "description": "что-то важное",
                "img": "https://i.ibb.co/JkW4tYy/Image-2.jpg",
            },
            {
                "uuid": "f78105fe-350b-4e13-8058-335d55c89a2a",
                "title": "Математический Анализ 2",
                "description": "что-то важное",
                "img": "https://i.ibb.co/JkW4tYy/Image-2.jpg",
            },
            {
                "uuid": "f78105fe-350b-4e13-8058-335d55c89a3a",
                "title": "Математический Анализ 3",
                "description": "что-то важное",
                "img": "https://i.ibb.co/JkW4tYy/Image-2.jpg",
            },
            {
                "uuid": "f78105fe-350b-4e13-8058-335d55c89a4a",
                "title": "Математический Анализ 4",
                "description": "что-то важное",
                "img": "https://i.ibb.co/JkW4tYy/Image-2.jpg",
            },
        ], {status: 200}
    );
    if (response.status === 200) {
        return response
    } else {
        throw new Response("Not Found", { status: response.status });
    }
}