import React from 'react';
import { Jumbotron, Carousel, Row, Tab, Col, Nav, Container, Image } from 'react-bootstrap';

import image00 from './imgs/00.png';
import image01 from './imgs/screenshots/01.png';
import image02 from './imgs/screenshots/02.png';
import image03 from './imgs/screenshots/03.png';
import image04 from './imgs/screenshots/04.png';
import image05 from './imgs/screenshots/05.png';
import image06 from './imgs/screenshots/06.png';
import image07 from './imgs/screenshots/07.png';
import image08 from './imgs/screenshots/08.png';
import image09 from './imgs/screenshots/09.png';
import image10 from './imgs/screenshots/10.png';
import image11 from './imgs/screenshots/11.png';
import image12 from './imgs/screenshots/12.png';
import image13 from './imgs/screenshots/13.png';
import image14 from './imgs/screenshots/14.png';
import image15 from './imgs/screenshots/15.png';
import image16 from './imgs/screenshots/16.png';
import './Home.css';

const Home = () => {
    const setImage = (img) => {
        var modal = document.getElementById("myModal");
        var modalImg = document.getElementById("img01");
        modal.style.display = "block";
        modalImg.src = img;
    }
    return (
        <Row>
            <Jumbotron>
                <Carousel fluid>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src={image01}
                            alt="First slide"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src={image02}
                            alt="First slide"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src={image03}
                            alt="First slide"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src={image04}
                            alt="First slide"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src={image05}
                            alt="First slide"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src={image06}
                            alt="First slide"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src={image07}
                            alt="First slide"
                        />
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="d-block w-100"
                            src={image08}
                            alt="First slide"
                        />
                    </Carousel.Item>
                </Carousel>
            </Jumbotron>
            <Tab.Container id="left-tabs-example" defaultActiveKey="first">
                <Row>
                    <Col sm={4}>
                        <Nav variant="pills" className="flex-column">
                            <Nav.Item>
                                <Nav.Link eventKey="first">Overview</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                                <Nav.Link eventKey="second">Stateful Approach</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                                <Nav.Link eventKey="third">Stateless Approach</Nav.Link>
                            </Nav.Item>
                        </Nav>
                    </Col>
                    <Col sm={8}>
                        <Tab.Content>
                            <Tab.Pane eventKey="first">
                                <h1>Project Overview</h1>
                                <p>A Sample Quick Start Project Test.</p>
                                <p>
                                    Github repository: <a href="https://github.com/hamzajg/ProductsQuickTest" target="_blank" >HERE</a>
                                </p>
                                <h2>Architecture</h2>
                                <Container>
                                    <Row>
                                        <Col xs={12} md={12}>
                                            <Image src={image00} onClick={() => setImage(image00)} style={{cursor: "pointer"}} thumbnail />
                                        </Col>
                                    </Row>
                                </Container>
                                <p>Domain Centric</p>
                                <p>Event Driven</p>
                                <Container>
                                    <Row>
                                        <Col xs={6} md={6}>
                                            <Image src={image13} onClick={() => setImage(image13)} style={{cursor: "pointer"}} thumbnail />
                                        </Col>
                                        <Col xs={6} md={6}>
                                            <Image src={image14} onClick={() => setImage(image14)} style={{cursor: "pointer"}} thumbnail />
                                        </Col>
                                    </Row>
                                </Container>
                                <p>CQRS</p>
                                <p>BFF</p>
                                <h3>Endpoints & Application Diagram</h3>
                                <Container>
                                    <Row>
                                        <Col xs={6} md={6}>
                                            <Image src={image11} onClick={() => setImage(image11)} style={{cursor: "pointer"}} thumbnail />
                                        </Col>
                                        <Col xs={6} md={6}>
                                            <Image src={image12} onClick={() => setImage(image12)} style={{cursor: "pointer"}} thumbnail />
                                        </Col>
                                    </Row>
                                </Container>

                                <h2>Reposotory Stratigies</h2>
                                <p>InMemory State Repository</p>
                                <p>InMemory Event Store</p>
                                <h2>Solution Structure</h2>
                                <Container>
                                    <Row>
                                        <Col xs={6} md={6}>
                                            <Image src={image09} onClick={() => setImage(image09)} style={{cursor: "pointer"}} thumbnail />
                                        </Col>
                                        <Col xs={6} md={6}>
                                            <Image src={image10} onClick={() => setImage(image10)} style={{cursor: "pointer"}} thumbnail />
                                        </Col>
                                    </Row>
                                </Container>
                                <h2>FrontEnd Structure</h2>
                                <Container>
                                    <Row>
                                        <Col xs={6} md={6}>
                                            <Image src={image15} onClick={() => setImage(image15)} style={{cursor: "pointer"}} thumbnail />
                                        </Col>
                                        <Col xs={6} md={6}>
                                            <Image src={image16} onClick={() => setImage(image16)} style={{cursor: "pointer"}} thumbnail />
                                        </Col>
                                    </Row>
                                </Container>
                            </Tab.Pane>
                            <Tab.Pane eventKey="second">

                            </Tab.Pane>
                            <Tab.Pane eventKey="third">

                            </Tab.Pane>
                        </Tab.Content>
                    </Col>
                </Row>
            </Tab.Container>

            <div id="myModal" className="modal">

                <span className="close" onClick={() => document.getElementById("myModal").style.display = "none"}>&times;</span>

                <Image fluid className="modal-content" id="img01" />

            </div>
        </Row>
    )
}
export default Home;

