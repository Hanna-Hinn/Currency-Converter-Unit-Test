import Card from "@mui/material/Card";
import Box from "@mui/material/Box";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import FormGroup from "@mui/material/FormGroup";
import Radio from "@mui/material/Radio";
import {
  FormControl,
  InputLabel,
  TextField,
  Button,
  Select,
  MenuItem,
  FormLabel,
  RadioGroup,
  FormControlLabel,
} from "@mui/material";
import CurrencyExchangeIcon from "@mui/icons-material/CurrencyExchange";
import axios from "axios";
import "./App.css";
import { useEffect, useState } from "react";

function App() {
  const [currencies, setCurrencies] = useState(["USD", "JOD", "EUR"]);
  const [source, setSource] = useState("");
  const [target, setTarget] = useState("");
  const [rate, setRate] = useState(0);
  const [amount, setAmount] = useState(0);
  const [rateSource, setRateSource] = useState("API");

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/currency/shorthand")
      .then((response) => {
        setCurrencies(response.data.sort());
      });
  }, []);

  const handleSourceChange = (event) => {
    setSource(event.target.value);
  };

  const handleTargetChange = (event) => {
    setTarget(event.target.value);
  };

  const handleAmount = (event) => {
    setAmount(event.target.value);
  };

  const handleRateSource = (event) => {
    setRateSource(event.target.value);
  };

  const handleSubmit = (event) => {
    console.log("TEST");
    axios
      .get(
        `http://localhost:8080/api/v1/currency/exchange?targetCurrency=${target}&sourceCurrency=${source}&rateSource=${rateSource}`
      )
      .then((response) => {
        setRate(response.data);
      });
  };

  const handleUpdate = (event) => {

  };

  return (
    <Box id="content">
      <Card id="card">
        <CardContent className="card-content">
          <Typography
            variant="h6"
            gutterBottom
            className="card-content-elements"
            sx={{ flexWrap: "wrap" }}
          >
            Currency Exchange Calculator
          </Typography>
          <img
            className="card-content-elements"
            src={"./images/pngegg.png"}
            alt="Currency Exchange Logo"
            width="100"
            sx={{
              float: "right",
              width: "42px",
              height: "42px",
              flexBasis: "50%",
            }}
          />
        </CardContent>
        <CardActions sx={{ width: "100%" }}>
          <FormGroup>
            {/* Desired Amount */}
            <FormControl className="form-element">
              <TextField
                variant="standard"
                label="Desired Amount:"
                type="number"
                value={amount}
                onChange={handleAmount}
              />
            </FormControl>
            {/* Source Currency */}
            <FormControl className="form-element">
              <InputLabel id="demo-simple-select-standard-label1">
                Source Currency (From) :
              </InputLabel>
              <Select
                labelId="demo-simple-select-standard-label1"
                label="Source Currency (From) :"
                value={source}
                onChange={handleSourceChange}
              >
                {currencies &&
                  currencies.map((item) => {
                    return <MenuItem value={item}>{item}</MenuItem>;
                  })}
              </Select>
            </FormControl>
            {/* Target Currency */}
            <FormControl className="form-element">
              <InputLabel id="demo-simple-select-standard-label2">
                Target Currency (To) :
              </InputLabel>
              <Select
                labelId="demo-simple-select-standard-label2"
                label="Target Currency (To) :"
                value={target}
                onChange={handleTargetChange}
              >
                {currencies &&
                  currencies.map((item) => {
                    return <MenuItem value={item}>{item}</MenuItem>;
                  })}
              </Select>
            </FormControl>
            {/* Choose Rate Source */}
            <FormLabel id="demo-controlled-radio-buttons-group">
              Rate Source:
            </FormLabel>
            <RadioGroup
              aria-labelledby="demo-controlled-radio-buttons-group"
              name="controlled-radio-buttons-group"
              value={rateSource}
              onChange={handleRateSource}
            >
              <FormControlLabel value="DB" control={<Radio />} label="DB" />
              <FormControlLabel value="API" control={<Radio />} label="API" />
            </RadioGroup>
            {/* Submit or convert */}
            <Button
              className="form-element"
              variant="contained"
              onClick={handleSubmit}
            >
              <CurrencyExchangeIcon />
              Exchange
            </Button>
            {/* Result */}
            <FormControl className="form-element">
              <TextField
                variant="standard"
                disabled
                label="Result:"
                value={rate * amount}
              />
            </FormControl>
            {/* Rate Used */}
            <FormControl className="form-element">
              <TextField
                variant="standard"
                disabled
                label="Rate Used:"
                value={rate}
              />
            </FormControl>
            <Box component="span" sx={{ p: 2, border: "1px dashed grey" }}>
              <Button onClick={handleUpdate}>Update DB</Button>
            </Box>
          </FormGroup>
        </CardActions>
      </Card>
    </Box>
  );
}

export default App;
