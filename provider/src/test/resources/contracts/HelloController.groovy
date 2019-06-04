import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url('/info') {
            queryParameters {
                parameter("id", "star")
            }
        }

    }
    response {
        status 200
        body("hello star")
        headers {
            header('Content-Type': 'application/json;charset=UTF-8')
        }
    }
}