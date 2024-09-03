package step05

class Contract {
    var id: Long?
    var name: String
    var cost: Int

    constructor(_id: Long, _name: String, _cost: Int) {
        id = _id
        name = _name
        cost = _cost
    }

    constructor(_id: Long, _name: String) {
        id = _id
        name = _name
        cost = 0
    }
}
