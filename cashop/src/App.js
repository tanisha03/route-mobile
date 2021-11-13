import Header from './components/Header';
import Home from './pages/Home';
import Offers from './pages/Offers';
import Campaigns from './pages/Campaigns';
import Transactions from './pages/Transactions';
import './App.css';
import {
  Routes,
  Route,
} from "react-router-dom";

function App() {
  return (
      <div className="App">
      <Header/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="offers" element={<Offers />} />
        <Route path="campaigns" element={<Campaigns />} />
        <Route path="transactions" element={<Transactions />} />
      </Routes>
      </div>
  );
}

export default App;
