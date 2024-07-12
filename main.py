from flask import Flask, jsonify


app = Flask(__name__)


@app.route("/")
def home():
    lenguajesFav = ["Python", "Java", "HTML", "JAVASCRIPT"]
    return jsonify(lenguajesFav)

if __name__ == '__main__':
    app.run(debug=True)